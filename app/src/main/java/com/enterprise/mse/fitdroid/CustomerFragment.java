package com.enterprise.mse.fitdroid;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.UUID;
import java.util.jar.Manifest;

/**
 * Created by mikeellis on 8/26/17.
 * this is the controller for the customer info panel
 */

public class CustomerFragment extends Fragment {

    private Customer mCustomer;
    private final String TAG = "CustomerFragment";
    private final static String ARG_CUSTOMER_ID = "customerID";
    private final static String FILE_PROVIDER = "com.enterprise.mse.fitdroid.fileprovider";
    private static final int REQUEST_PHOTO = 0;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_PHONE_CALL = 2;
    private static final int REQUST_EMAIL = 3;



    private boolean mNewCustomer;

    //UI controls
    private EditText mCustomerName;
    private EditText mCustomerEmail;
    private EditText mCustomerAddr;
    private EditText mCustomerPhone;
    private Button mSaveButton;
    private ImageButton mAddImage;
    private File mPhotoFile;
    private ImageView mImageView;
    private ImageButton mEmailButton;
    private ImageButton mPhoneButton;


    // onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null && args.containsKey(ARG_CUSTOMER_ID)) {
            UUID customerID = (UUID)args.getSerializable(ARG_CUSTOMER_ID);
            mCustomer = CustomerList.getCustomerList(getActivity()).getCustomer(customerID);
            mNewCustomer = false;
        } else {
            Log.d(TAG,"creating new customer");
            mCustomer = new Customer();
            mNewCustomer = true;
        }

        mPhotoFile = CustomerList.getCustomerList(getActivity()).getPhotoFile(mCustomer);

    }

    // onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstance) {

        // inflate the Customer View
        View v = inflater.inflate(R.layout.fragment_customer_info,container,false);

        // get references to all UI components
        mCustomerName = (EditText) v.findViewById(R.id.customer_name_text);
        mCustomerEmail = (EditText) v.findViewById(R.id.customer_email_input);
        mCustomerAddr = (EditText) v.findViewById(R.id.customer_address_input);
        mCustomerPhone = (EditText) v.findViewById(R.id.customer_phone_input);
        mAddImage = (ImageButton) v.findViewById(R.id.customer_add_img);
        mSaveButton = (Button) v.findViewById(R.id.customer_save_btn);
        mImageView = (ImageView) v.findViewById(R.id.customer_image_view);
        mEmailButton = (ImageButton) v.findViewById(R.id.customer_email_btn);
        mPhoneButton = (ImageButton) v.findViewById(R.id.customer_phone_img);

        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final ShareCompat.IntentBuilder sendEmail =  ShareCompat.IntentBuilder.from(getActivity());
        final Intent callPhone = new Intent(Intent.ACTION_DIAL);



        PackageManager packageManager = getActivity().getPackageManager();
        // determine if a photo can be taken
        boolean canTakePhoto = mPhotoFile != null
                && captureImage.resolveActivity(packageManager) != null;
        // determine if phone call can be made
        boolean canCall = callPhone.resolveActivity(packageManager) != null;
        //determine if email can be sent
        boolean canEmail = sendEmail.getIntent().resolveActivity(packageManager) != null;

        // disable the buttons where actions can't be performed

        mEmailButton.setEnabled(canEmail);
        mPhoneButton.setEnabled(canCall);

        // if existing customer, fill the values
        if(!mNewCustomer) {
            mCustomerName.setText(mCustomer.getCustomerName());
            mCustomerEmail.setText(mCustomer.getEmail());
            mCustomerAddr.setText(mCustomer.getAddress());
            mCustomerPhone.setText(mCustomer.getPhoneNumber());
            //TODO image
        }
        // add all the listeners
        mCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // update the customer name
                mCustomer.setCustomerName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCustomerEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCustomer.setEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCustomerAddr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCustomer.setAddress(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCustomerPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG,"setting phone number: " + charSequence.toString());
                mCustomer.setPhoneNumber(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mAddImage.setEnabled(canTakePhoto);
        // take photo button
        mAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"photo button clicked");

                // check to make sure the user granted camera permissions
                int permission = checkPermissions(android.Manifest.permission.CAMERA);
                if(permission == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(new String[] {android.Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION);

                } else {
                    takePhoto();
                }




            }
        });

        // setup the email button to open a new email
        mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"email button clicked");
                    if(mCustomer.getEmail() != null){
                        sendEmail.setEmailTo(new String[]{mCustomer.getEmail()});
                        sendEmail.setSubject(getString(R.string.email_default_subject));
                        sendEmail.createChooserIntent();
                        startActivity(sendEmail.getIntent());
                    }
            }
        });

        //setup the phone butotn
        mPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mCustomer.getPhoneNumber() != null) {
                    callPhone.setData(Uri.parse("tel:" + mCustomer.getPhoneNumber()));
                    startActivity(callPhone);
                }
            }
        });


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"save button clicked");
                CustomerList.getCustomerList(getActivity()).updateCustomer(mCustomer);


            }
        });

        updatePhotoView();

        return v;
    }


    //onPause:  save info to DB
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause called");
        CustomerList.getCustomerList(getActivity()).updateCustomer(mCustomer);
    }

    // return new instance using customer id
    public static CustomerFragment newInstance(UUID customerID) {
        // create a new customer fragment
        CustomerFragment fragment = new CustomerFragment();
        // if customerID supplied, place it in an args bundle
        if(customerID != null) {
            Bundle args = new Bundle();
            args.putSerializable(ARG_CUSTOMER_ID,customerID);
            fragment.setArguments(args);
        }

        return fragment;
    }

    // check for permissions
    private int checkPermissions(String permission) {
        return ContextCompat.checkSelfPermission(getActivity(),permission);
    }

    //handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        switch(requestCode) {
            case REQUEST_CAMERA_PERMISSION:{
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();

                } else {
                    photoDenied();
                }
            }
        }
    }

    private void takePhoto() {
        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = FileProvider.getUriForFile(getActivity(),FILE_PROVIDER,mPhotoFile);
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);

        List<ResolveInfo> cameraActivities = getActivity()
                .getPackageManager()
                .queryIntentActivities(imageIntent,PackageManager.MATCH_DEFAULT_ONLY);
        // grant permission to all the possible camera activties
        for(ResolveInfo activity : cameraActivities){
            getActivity().grantUriPermission(activity.activityInfo.packageName,
                    uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }

        startActivityForResult(imageIntent,REQUEST_PHOTO);
    }


    private void photoDenied() {

        Toast.makeText(getActivity(),getString(R.string.photo_permission_denied),Toast.LENGTH_LONG).show();
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mImageView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(),getActivity());
            mImageView.setImageBitmap(bitmap);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK)
            return;

        if(requestCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(getActivity(),FILE_PROVIDER,mPhotoFile);
            getActivity().revokeUriPermission(uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updatePhotoView();
        }

    }
}

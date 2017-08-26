package com.enterprise.mse.fitdroid;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 8/24/17.
 */

public class SessionList {

    // instance variable - a session list cannot be instatiated directly
    private static SessionList sSessionList;
    // list of sessions
    private List<Session> mSessionList;

    public static SessionList getSessionList(Context context) {
        if (sSessionList == null)
             sSessionList = new SessionList(context);
        return sSessionList;
    }

    // ctor is private
    private SessionList(Context context ) {
        // creates the array list for the sessions
        mSessionList = new ArrayList<>();
        Session session = new Session();
        mSessionList.add(session);
    }
}

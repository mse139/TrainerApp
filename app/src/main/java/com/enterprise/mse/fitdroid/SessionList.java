package com.enterprise.mse.fitdroid;

import android.content.Context;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 8/24/17.
 */

public class SessionList {

    // instance variable - a session list cannot be instantiated directly
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

        // add some test sessions
        for(int i = 1 ; i < 25; i++) {
            Session session = new Session();
            session.setSessionDateTime(new Date());
            session.setCost(5.1*i);
            if(i%2 == 0){
                session.setComplete(true);
                session.setPaid(false);
            }

            else {
                session.setComplete(false);
                session.setPaid(true);
            }
            session.setNotes("this is session # " + Integer.toString(i));
            session.setCustomerID(UUID.randomUUID());
            mSessionList.add(session);
        }


    }

    // return the list of sessions
    public List<Session> getSessions() {
        return mSessionList;
    }

    // search the list for a given session
    public Session getSession(UUID id) {
        for(Session session: mSessionList) {
            if(session.getSessionID().equals(id) )
                return session;
        }

        return null;
    }

    // search list for given customer id - returns a list
    public List<Session> getSessionsByCustomer(UUID customerID) {
        List<Session> list = new ArrayList<>();

        for(Session session: mSessionList) {
            if(session.getCustomerID().equals(customerID))
                list.add(session);
        }

        return list;
    }

    // add a new session
    public void addSession(Session s) {
        mSessionList.add(s);
    }
}

/**
 * 
 * Phonegap share plugin for Android
 * Kevin Schaul 2011
 *
 */

package com.schaul.plugins.share;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import org.apache.cordova.api.*;

public class Share extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		try {
			JSONObject jo = args.getJSONObject(0);
			doSendIntent(jo.getString("subject"), jo.getString("text")); 
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
			return true;
		} catch (JSONException e) {
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
			return false;
		}
	}

	private void doSendIntent(String subject, String text) {
		Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
		sendIntent.setType("text/plain");
		sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
		sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
		this.cordova.startActivityForResult(this, sendIntent, 0);
	}

}
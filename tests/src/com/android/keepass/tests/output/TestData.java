/*
 * Copyright 2009 Brian Pellin.
 *     
 * This file is part of KeePassDroid.
 *
 *  KeePassDroid is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  KeePassDroid is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePassDroid.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.android.keepass.tests.output;

import java.io.IOException;
import java.io.InputStream;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.phoneid.keepassj2me.ImporterV3;
import org.phoneid.keepassj2me.PwManager;

import android.content.Context;
import android.content.res.AssetManager;

import com.android.keepass.Database;
import com.android.keepass.keepasslib.InvalidKeyFileException;

public class TestData {
	private static final String TEST1_KEYFILE = "";
	private static final String TEST1_KDB = "test1.kdb";
	private static final String TEST1_PASSWORD = "12345";

	private static Database mDb;
	
	public static Database GetDb1(Context ctx) throws IOException, InvalidCipherTextException, InvalidKeyFileException {
		if ( mDb == null ) {
			AssetManager am = ctx.getAssets();
			InputStream is = am.open(TEST1_KDB, AssetManager.ACCESS_STREAMING);

			mDb = new Database();
			mDb.LoadData(ctx, is, TEST1_PASSWORD, TEST1_KEYFILE, ImporterV3.DEBUG);
			mDb.mFilename = "/sdcard/test1.kdb";
		}
		
		return mDb;
		
	}
	
	public static PwManager GetTest1(Context ctx) throws InvalidCipherTextException, IOException, InvalidKeyFileException {
		if ( mDb == null ) {
			GetDb1(ctx);
		}
		
		return mDb.mPM;
	}
}

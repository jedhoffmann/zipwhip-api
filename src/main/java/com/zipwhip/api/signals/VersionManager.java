package com.zipwhip.api.signals;

import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: jed Date: 6/24/11 Time: 4:33 PM
 * <p/>
 * This class will record what versions have been seen before. Every signal has
 * a version. It is possible to have multiple versionKeys if you have more than
 * one subscription.
 * <p/>
 * In an android environment, you will want to implement this via
 * SharedPreferences, or SQLite. In a Java application, you might want to use a
 * File.
 */
public interface VersionManager {

    /**
     * Get all the current highest versions per key.
     * 
     * @return the current highest version number for each key or an empty list
     */
    Map<String, Long> getVersions();

    /**
     * Set the version if versionKey is new. Increment the version number if
     * newVersion is greater than the previous version.
     * 
     * @param versionKey
     * @param newVersion
     * @return true if the version was set or incremented otherwise false.
     */
    boolean setVersion(String versionKey, Long newVersion);

    /**
     * Invalidate all keys and versions in the implemented persistence solution.
     */
    void clearVersions();

}

package org.gradle.api.plugins.dbdeploy

/**
 * Created by Sion on 13/04/2014.
 */
class DbDeployPluginExtension {
    File scriptdirectory = new File('src/main/sql')
    String encoding
    String driver
    String url
    String password
    String userid
    String changeLogTableName
    String delimiter
    String delimiterType
    String lineEnding
    Long lastChangeToApply
    File outputfile
    String dbms
    File undoOutputfile
    File templateDirectory
    String name = "new_change_script"
}

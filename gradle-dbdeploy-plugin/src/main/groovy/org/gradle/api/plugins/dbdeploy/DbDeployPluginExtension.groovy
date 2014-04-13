package org.gradle.api.plugins.dbdeploy

/**
 * Created by Sion on 13/04/2014.
 */
class DbDeployPluginExtension {
    // Full or relative path to the directory containing the delta scripts.
    File scriptdirectory

    // Encoding to use for change scripts and output files.
    String encoding

    // Specifies the jdbc driver.
    String driver

    // Specifies the url of the database that the deltas are to be applied to.
    String url

    // The password of the dbms user who has permissions to select from the
    // schema version table.
    String password

    // The ID of a dbms user who has permissions to select from the schema
    // version table.
    String userid

    // The name of the changelog table to use. Useful if you need to separate
    // DDL and DML when deploying to replicated environments. If not supplied
    // defaults to "changelog"
    String changeLogTableName

    // Delimiter to use to separate scripts into statements, if dbdeploy will
    // apply the scripts for you i.e. you haven't specified outputfile. Default ;
    String delimiter

    // Either normal: split on delimiter wherever it occurs or row  only split
    // on delimiter if it features on a line by itself. Default normal.
    String delimiterType

    // Line ending to separate indiviual statement lines when applying directly
    // to the database. Can be platform (the default line ending for the current platform),
    // cr, crlf or lf. Default platform.
    String lineEnding

    // The highest numbered delta script to apply
    Long lastChangeToApply

    /* Used with the CreateDatabaseScripts Task */
    // The name of the script that dbdeploy will output. Include a full
    // or relative path.
    File outputfile

    // String representing our DBMS (e.g. mysql, ora)
    String dbms

    // The name of the undo script that dbdeploy will output. Include a full
    // or relative path.
    File undoOutputfile

    // Directory for your template scripts, if not using built-in
    File templateDirectory
}

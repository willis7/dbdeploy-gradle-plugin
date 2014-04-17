package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.DbDeploy
import com.dbdeploy.database.DelimiterType
import com.dbdeploy.database.LineEnding
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

/**
 * Abstract class that all dbdeploy database tasks should extend.
 * @author Sion Williams
 */
abstract class DbDeployTask extends DefaultTask{

    /*
    * Full or relative path to the directory containing the delta scripts.
    */
    @InputDirectory
    File scriptdirectory

    /*
    * Encoding to use for change scripts and output files.
    */
    @Input
    @Optional
    String encoding

    /*
    * Specifies the jdbc driver.
    */
    @Input
    String driver

    /*
    * Specifies the url of the database that the deltas are to be applied to.
    */
    @Input
    String url

    /*
    * The password of the dbms user who has permissions to select from the
    * schema version table.
    */
    @Input
    String password

    /*
    * The ID of a dbms user who has permissions to select from the schema
    * version table.
    * */
    @Input
    String userid

    /*
    * The name of the changelog table to use. Useful if you need to separate
    * DDL and DML when deploying to replicated environments. If not supplied
    * defaults to "changelog"
    */
    @Input
    @Optional
    String changeLogTableName

    /*
    * Delimiter to use to separate scripts into statements, if dbdeploy will
    * apply the scripts for you i.e. you haven't specified outputfile. Default ;
    */
    @Input
    @Optional
    String delimiter

    /*
    * Either normal: split on delimiter wherever it occurs or row  only split
    * on delimiter if it features on a line by itself. Default normal.
    */
    @Input
    @Optional
    String delimiterType

    /*
    * Line ending to separate indiviual statement lines when applying directly
    * to the database. Can be platform (the default line ending for the current platform),
    * cr, crlf or lf. Default platform.
    */
    @Input
    @Optional
    String lineEnding

    /*
    * The highest numbered delta script to apply
    */
    @Input
    @Optional
    Long lastChangeToApply

    DbDeployTask (String description){
        this.description = description
        group = 'DbDeploy'
    }

    protected DbDeploy getConfiguredDbDeploy() {
        DbDeploy dbDeploy = new DbDeploy()
        /* Properties set by convention mapping need to explicitly
        *  use getter methods*/
        dbDeploy.setScriptdirectory(getScriptdirectory())
        dbDeploy.setDriver(getDriver())
        dbDeploy.setUrl(getUrl())
        dbDeploy.setPassword(getPassword())
        dbDeploy.setUserid(getUserid())

        if (encoding != null) {
            dbDeploy.setEncoding(getEncoding())
        }

        if (lastChangeToApply != null) {
            dbDeploy.setLastChangeToApply(getLastChangeToApply())
        }

        if (changeLogTableName != null) {
            dbDeploy.setChangeLogTableName(getChangeLogTableName())
        }

        if (delimiter != null) {
            dbDeploy.setDelimiter(getDelimiter())
        }

        if (delimiterType != null) {
            dbDeploy.setDelimiterType(DelimiterType.valueOf(getDelimiterType()))
        }

        if (lineEnding != null) {
            dbDeploy.setLineEnding(LineEnding.valueOf(getLineEnding()))
        }

        return dbDeploy
    }

    @TaskAction
    void start() {
        withExceptionHandling {
            executeAction()
        }
    }

    public void withExceptionHandling(Closure c){
        try{
            c()
        } catch(Exception e) {
            logger.error(e)
            throw new GradleException(e.message)
        }
    }

    abstract void executeAction()
}

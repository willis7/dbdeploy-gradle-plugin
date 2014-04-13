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

    @InputDirectory File scriptdirectory
    @Input @Optional String encoding
    @Input String driver
    @Input String url
    @Input String password
    @Input String userid
    @Input @Optional String changeLogTableName
    @Input @Optional String delimiter
    @Input @Optional String delimiterType
    @Input @Optional String lineEnding
    @Input @Optional Long lastChangeToApply

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
            throw new GradleException(e.message)
        }
    }

    abstract void executeAction()
}

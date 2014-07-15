package org.gradle.api.plugins.dbdeploy

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.dbdeploy.tasks.CreateChangeScriptTask
import org.gradle.api.plugins.dbdeploy.tasks.CreateDatabaseScriptsTask
import org.gradle.api.plugins.dbdeploy.tasks.AbstractDbDeployTask
import org.gradle.api.plugins.dbdeploy.tasks.UpdateDatabaseTask

/**
 * Created by Sion on 13/04/2014.
 */
class DbDeployPlugin implements Plugin<Project>{
    static final EXTENSION_NAME = 'dbdeploy'
    static final DBSCRIPTS_TASK_NAME = 'dbScripts'
    static final UPDATE_TASK_NAME ='update'
    static final CHANGE_TASK_NAME ='changeScript'

    @Override
    void apply( Project project ){
        project.extensions.create( EXTENSION_NAME, DbDeployPluginExtension )
        addDbDeployCommonConvention( project )
    }

    private void addDbDeployCommonConvention(Project project){
        /*
         * Adds task after project is evaluated to ensure that
         * extension values are set.
         */
        project.tasks.withType( AbstractDbDeployTask ){
            /*
             * For all DbDeploy tasks we automatically assign the following
             */
            def extension = project.extensions.findByName( EXTENSION_NAME )
            /*
             * Assigning the extension property value wrapped in a closure
             * to the tasks convention mapping.
             */
            conventionMapping.scriptdirectory = { extension.scriptdirectory }
            conventionMapping.encoding = { extension.encoding }
            conventionMapping.driver = { extension.driver }
            conventionMapping.url = { extension.url }
            conventionMapping.password = { extension.password }
            conventionMapping.userid = { extension.userid }
            conventionMapping.changeLogTableName = { extension.changeLogTableName }
            conventionMapping.delimiter = { extension.delimiter }
            conventionMapping.delimiterType = { extension.delimiterType }
            conventionMapping.lineEnding = { extension.lineEnding }
            conventionMapping.lastChangeToApply = { extension.lastChangeToApply }
        }
        addDbDeployTasks( project )
    }

    private void addDbDeployTasks( Project project ){

        project.task( DBSCRIPTS_TASK_NAME, type: CreateDatabaseScriptsTask){
            conventionMapping.outputfile = { extension.outputfile }
            conventionMapping.dbms = { extension.dbms }
            conventionMapping.undoOutputfile = { extension.undoOutputfile }
            conventionMapping.templateDirectory = { extension.templateDirectory }
        }

        project.task( CHANGE_TASK_NAME, type: CreateChangeScriptTask ){
            conventionMapping.nameSuffix = { extension.nameSuffix }
        }

        project.task( UPDATE_TASK_NAME, type: UpdateDatabaseTask )
    }
}

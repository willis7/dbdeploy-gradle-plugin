package org.gradle.api.plugins.dbdeploy

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Created by Sion on 13/04/2014.
 */
class DbDeployPluginTest extends Specification {
    static final DBSCRIPTS_TASK_NAME = 'dbScripts'
    static final UPDATE_TASK_NAME ='update'
    Project project

    void setup() {
        project = ProjectBuilder.builder().build()
    }

    def "Applies plugin and sets extension values"() {
        expect: 'no dbdeploy tasks in the project initially'
            project.tasks.findByName(DBSCRIPTS_TASK_NAME) == null
            project.tasks.findByName(UPDATE_TASK_NAME) == null

        when: 'I explicitly add the project and set extension values'
            project.apply plugin: 'DbDeploy'

            project.dbdeploy {
                scriptdirectory = new File('.')
                driver = 'org.hsqldb.jdbcDriver'
                url = 'jdbc:hsqldb:file:db/testdb;shutdown=true'
                password = ''
                userid = 'sa'
            }

        then: 'dbdeploy tasks and properties should be available'
            project.extensions.findByName(DbDeployPlugin.EXTENSION_NAME) != null

            Task updateTask = project.tasks.findByName(UPDATE_TASK_NAME)
            updateTask != null
            updateTask.group == 'DbDeploy'
            updateTask.description == 'Apply dbdeploy change scripts to the database.'
            updateTask.scriptdirectory == new File('.')
            updateTask.driver == 'org.hsqldb.jdbcDriver'
            updateTask.url == 'jdbc:hsqldb:file:db/testdb;shutdown=true'
            updateTask.password == ''
            updateTask.userid == 'sa'

    }
}

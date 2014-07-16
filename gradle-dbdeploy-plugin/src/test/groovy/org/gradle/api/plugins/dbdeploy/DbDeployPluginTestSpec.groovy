package org.gradle.api.plugins.dbdeploy

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * @author Sion Williams
 */
class DbDeployPluginTestSpec extends Specification {

    Project project

    void setup() {
        project = ProjectBuilder.builder().build()
    }

    def 'Applies plugin and checks created tasks'() {
        expect: 'no dbdeploy tasks in the project initially'
            project.tasks.findByName( DbDeployPlugin.DBSCRIPTS_TASK_NAME ) == null
            project.tasks.findByName( DbDeployPlugin.UPDATE_TASK_NAME ) == null
            project.tasks.findByName( DbDeployPlugin.CHANGE_TASK_NAME ) == null

        when: 'I explicitly add the project'
            project.apply plugin: 'dbdeploy'

        then: 'three dbdeploy tasks should be added'
            project.extensions.findByName(DbDeployPlugin.EXTENSION_NAME) != null
            project.tasks.findByName( DbDeployPlugin.DBSCRIPTS_TASK_NAME )
            project.tasks.findByName( DbDeployPlugin.UPDATE_TASK_NAME )
            project.tasks.findByName( DbDeployPlugin.CHANGE_TASK_NAME )
    }

    // TODO: Fix failing unit test
    def 'Applies plugin and sets default values for changeScript task'() {
        expect: 'no dbdeploy tasks in the project initially'
        project.tasks.findByName( DbDeployPlugin.CHANGE_TASK_NAME ) == null

        when: 'I explicitly add the project'
        project.apply plugin: 'dbdeploy'

        then: 'dbdeploy tasks and properties should be available'
        project.extensions.findByName(DbDeployPlugin.EXTENSION_NAME) != null
        Task task = project.tasks.findByName( DbDeployPlugin.CHANGE_TASK_NAME )
        task != null
        task.group == 'DbDeploy'
        task.description == 'Generate a new timestamped dbdeploy change script'
        task.scriptdirectory == new File('src/main/sql')
        task.driver == null
        task.url == null
        task.password == null
        task.userid == null
        task.changeLogTableName == 'changelog'
        task.encoding == null
        task.delimiter == null
        task.delimiterType == null
        task.lineEnding == null
        task.lastChangeToApply == null
        task.nameSuffix == "new_change_script"
    }

    def 'Applies plugin and sets default values for update task'() {
        expect: 'no dbdeploy tasks in the project initially'
            project.tasks.findByName( DbDeployPlugin.UPDATE_TASK_NAME ) == null

        when: 'I explicitly add the project'
            project.apply plugin: 'dbdeploy'

        then: 'dbdeploy tasks and properties should be available'
            project.extensions.findByName(DbDeployPlugin.EXTENSION_NAME) != null
            Task task = project.tasks.findByName( DbDeployPlugin.UPDATE_TASK_NAME )
            task != null
            task.group == 'DbDeploy'
            task.description == 'Apply dbdeploy change scripts to the database.'
            task.scriptdirectory == new File('src/main/sql')
            task.driver == null
            task.url == null
            task.password == null
            task.userid == null
            task.changeLogTableName == 'changelog'
            task.encoding == null
            task.delimiter == null
            task.delimiterType == null
            task.lineEnding == null
            task.lastChangeToApply == null
    }

    // TODO: Fix failing unit test
    def 'Applies plugin and sets default values for dbScripts task'() {
        expect: 'no dbdeploy tasks in the project initially'
            project.tasks.findByName( DbDeployPlugin.DBSCRIPTS_TASK_NAME ) == null

        when: 'I explicitly add the project'
            project.apply plugin: 'dbdeploy'

        then: 'dbdeploy tasks and properties should be available'
            project.extensions.findByName(DbDeployPlugin.EXTENSION_NAME) != null
            Task task = project.tasks.findByName( DbDeployPlugin.DBSCRIPTS_TASK_NAME )
            task != null
            task.group == 'DbDeploy'
            task.description == 'Create the apply and undo scripts.'
            task.scriptdirectory == new File('src/main/sql')
            task.driver == null
            task.url == null
            task.password == null
            task.userid == null
            task.changeLogTableName == 'changelog'
            task.encoding == null
            task.delimiter == null
            task.delimiterType == null
            task.lineEnding == null
            task.lastChangeToApply == null
            task.outputfile == null
            task.dbms == null
            task.undoOutputfile == null
            task.templateDirectory == null
    }

    def 'Applies plugin and sets extension values'() {
        expect: 'no dbdeploy tasks in the project initially'
            project.tasks.findByName( DbDeployPlugin.UPDATE_TASK_NAME ) == null

        when: 'I explicitly add the project and set extension values'
            project.apply plugin: 'dbdeploy'

            project.dbdeploy {
                scriptdirectory = new File('.')
                driver = 'org.hsqldb.jdbcDriver'
                url = 'jdbc:hsqldb:file:db/testdb;shutdown=true'
                password = ''
                userid = 'sa'
            }

        then: 'dbdeploy tasks and properties should be available'
            project.extensions.findByName(DbDeployPlugin.EXTENSION_NAME) != null
            Task task = project.tasks.findByName( DbDeployPlugin.UPDATE_TASK_NAME )
            task != null
            task.group == 'DbDeploy'
            task.description == 'Apply dbdeploy change scripts to the database.'
            task.scriptdirectory == new File('.')
            task.driver == 'org.hsqldb.jdbcDriver'
            task.url == 'jdbc:hsqldb:file:db/testdb;shutdown=true'
            task.password == ''
            task.userid == 'sa'
    }
}

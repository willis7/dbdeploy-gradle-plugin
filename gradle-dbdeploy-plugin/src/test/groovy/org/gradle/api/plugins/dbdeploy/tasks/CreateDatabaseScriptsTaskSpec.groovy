package org.gradle.api.plugins.dbdeploy.tasks

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Tests for the Create Database Scripts Gradle Task
 *
 * @author Sion Williams
 */
class CreateDatabaseScriptsTaskSpec extends Specification {
    static final TASK_NAME = 'dbScripts'
    Project project

    void setup() {
        project = ProjectBuilder.builder().build()
    }

    def 'Add a dbScripts task'(){
        expect:
            project.tasks.findByName( TASK_NAME ) == null

        when:
            project.task( TASK_NAME, type: CreateDatabaseScriptsTask ) {
                scriptdirectory = new File('src/dist')
                outputfile = new File('src/dist/out')
                dbms = 'mysql'
            }

        then:
            Task task = project.tasks.findByName( TASK_NAME )
            task != null
            task.description == 'Create the apply and undo scripts.'
            task.group == 'DbDeploy'
            task.scriptdirectory == new File( 'src/dist' )
            task.outputfile == new File( 'src/dist/out' )
            task.dbms == 'mysql'
    }

    /* TODO: Move the Run dbScripts task to an integration test
    void 'Run dbScripts task'(){
        expect:
            project.tasks.findByName( TASK_NAME ) == null

        when:
            project.task( TASK_NAME, type: CreateChangeScriptTask ) {
                scriptdirectory = new File('./gradle-dbdeploy-plugin/src/dist')
                outputfile = new File('.')
                dbms = 'mysql'
            }
            Task task = project.tasks.findByName( TASK_NAME )
            task.start()

        then:
            !thrown(GradleException)
            task != null
            task.description == 'Generate a new timestamped dbdeploy change script'
            task.group == 'DbDeploy'
    }
    */
}

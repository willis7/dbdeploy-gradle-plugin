package org.gradle.api.plugins.dbdeploy.tasks

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification


/**
 * Tests for the Create Change Script Gradle Task
 *
 * @author Sion Williams
 */
class CreateChangeScriptTaskSpec extends Specification {
    static final TASK_NAME = 'changeScript'
    Project project

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    void 'Adds createChangeScript task'(){
        expect:
            project.tasks.findByName( TASK_NAME ) == null

        when:
            project.task( TASK_NAME, type: CreateChangeScriptTask ) {
                scriptdirectory = new File( 'src/dist' )
                nameSuffix = 'test_'
            }

        then:
            Task task = project.tasks.findByName( TASK_NAME )
            task != null
            task.description == 'Generate a new timestamped dbdeploy change script'
            task.group == 'DbDeploy'
            task.scriptdirectory == new File( 'src/dist' )
            task.nameSuffix == 'test_'
    }

    /* TODO: Move the Run createChangeScript task to an integration test
    void 'Run createChangeScript task'(){
        expect:
            project.tasks.findByName( TASK_NAME ) == null

        when:
            project.task( TASK_NAME, type: CreateChangeScriptTask ) {
                scriptdirectory = new File( './gradle-dbdeploy-plugin/src/dist' )
            }
            Task task = project.tasks.findByName( TASK_NAME )
            task.start()

        then:
            !thrown(GradleException)
            task != null
            task.description == 'Generate a new timestamped dbdeploy change script'
            task.group == 'DbDeploy'
    }*/
}
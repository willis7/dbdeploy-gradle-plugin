package org.gradle.api.plugins.dbdeploy.tasks

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification


/**
 *
 * @author Sion Williams
 */
class CreateChangeScriptTaskSpec extends Specification {
    static final TASK_NAME = 'createChangeScript'
    Project project

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    void 'Adds createChangeScript task'(){
        expect:
            project.tasks.findByName(TASK_NAME) == null

        when:
            project.task(TASK_NAME, type: CreateChangeScriptTask) {
                scriptdirectory = new File('src/dist')
            }

        then:
            Task task = project.tasks.findByName(TASK_NAME)
            task != null
            task.description == 'Generate a new timestamped dbdeploy change script'
            task.group == 'DbDeploy'
    }

    void 'Run createChangeScript task'(){
        expect:
        project.tasks.findByName(TASK_NAME) == null

        when:
        project.task(TASK_NAME, type: CreateChangeScriptTask) {
            scriptdirectory = new File('D:\\src\\dbdeploy-gradle-plugin\\gradle-dbdeploy-plugin\\src\\dist')
        }
        Task task = project.tasks.findByName(TASK_NAME)
        task.start()

        then:
        task != null
        task.description == 'Generate a new timestamped dbdeploy change script'
        task.group == 'DbDeploy'
    }
}
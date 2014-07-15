package org.gradle.api.plugins.dbdeploy.tasks

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Created by Sion on 15/07/2014.
 */
class UpdateDatabaseTaskSpec extends Specification {
    static final TASK_NAME = 'update'
    Project project

    void setup() {
        project = ProjectBuilder.builder().build()
    }

    def 'Add a update task'(){
        expect:
            project.tasks.findByName( TASK_NAME ) == null

        when:
            project.task( TASK_NAME, type: UpdateDatabaseTask )

        then:
            Task task = project.tasks.findByName( TASK_NAME )
            task != null
            task.description == 'Apply dbdeploy change scripts to the database.'
            task.group == 'DbDeploy'
    }
}

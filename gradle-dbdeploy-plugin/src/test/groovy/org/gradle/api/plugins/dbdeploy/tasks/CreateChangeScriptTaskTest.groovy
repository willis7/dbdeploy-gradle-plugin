package org.gradle.api.plugins.dbdeploy.tasks

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Created by Sion on 12/04/2014.
 */
class CreateChangeScriptTaskTest extends Specification {
    private Project project

    void setup() {
        project = ProjectBuilder.builder().build()
    }

    def "Can add a CreateChangeScript task"(){
        when:
        def task = project.task('createChangeScript', type: CreateChangeScriptTask)

        then:
        task instanceof CreateChangeScriptTask
    }

    def "Can add a CreateDatabaseScripts task"(){
        when:
        def task = project.task('createDatabaseScripts', type: CreateDatabaseScriptsTask)

        then:
        task instanceof CreateDatabaseScriptsTask
    }
}

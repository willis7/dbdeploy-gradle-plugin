package org.gradle.api.plugins.dbdeploy.tasks

import org.gradle.api.Project
import org.gradle.api.Task
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
        expect:
            project.tasks.findByName('createChangeScript') == null

        when:
            project.task('createChangeScript', type: CreateChangeScriptTask){
                scriptdirectory = '.'
                driver = 'org.hsqldb.jdbcDriver'
                url = 'jdbc:hsqldb:file:db/testdb;shutdown=true'
                userid = 'sa'
                password = ''
                dbms = 'hsql'
            }

        then:
            Task task = project.tasks.findByName('createChangeScript')
            task.scriptdirectory == '.'
            task.driver == 'org.hsqldb.jdbcDriver'
            task.url == 'jdbc:hsqldb:file:db/testdb;shutdown=true'
            task.userid == 'sa'
            task.password == ''
            task.dbms == 'hsql'
    }

    def "Can add a CreateDatabaseScripts task"(){
        when:
            def task = project.task('createDatabaseScripts', type: CreateDatabaseScriptsTask)

        then:
            task instanceof CreateDatabaseScriptsTask
    }
}

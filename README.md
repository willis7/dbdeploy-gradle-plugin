# Gradle DbDeploy plugin

![Database Logo](http://builddoctorprod.files.wordpress.com/2010/01/75294154_24824e3395_m.jpg)

This plugin is a direct port from the Ant and Maven equivalents found at the [dbdeploy website](https://code.google.com/p/dbdeploy/wiki/GettingStarted)

dbdeploy is a Database Change Management tool. It’s for developers or DBAs who want to evolve their database design - or refactor their database -
in a simple, controlled, flexible and frequent manner.

## Build Status
[![No Maintenance Intended](http://unmaintained.tech/badge.svg)](http://unmaintained.tech/)
[![Build Status](https://travis-ci.org/willis7/dbdeploy-gradle-plugin.svg?branch=master)](https://travis-ci.org/willis7/dbdeploy-gradle-plugin)

## Usage

To use the plugin, configure your `build.gradle` script and add the plugin:
```groovy
    buildscript {
        repositories {
            mavenCentral()
            maven { url 'http://dl.bintray.com/sion5/gradle-plugins/' }
        }
        dependencies {
            classpath 'org.gradle.api.plugins.dbdeploy:DbDeployPlugin:VERSION'
        }
    }
    apply plugin: 'dbdeploy'
```

# Tasks
The plugin adds 3 tasks to your project; `dbScripts`, `changeScript` and `update`.

## Configuration

### build.gradle
```groovy
    dbdeploy {
            scriptdirectory = new File('.')
            driver = 'org.hsqldb.jdbcDriver'
            url = 'jdbc:hsqldb:file:db/testdb;shutdown=true'
            password = ''
            userid = 'sa'
    }
```

## Task properties
### generic properties

* `scriptdirectory` : Full or relative path to the directory containing the delta scripts.
* `encoding` : Encoding to use for change scripts and output files.
* `driver` : Specifies the jdbc driver.
* `url` : Specifies the url of the database that the deltas are to be applied to.
* `password` : The password of the dbms user who has permissions to select from the schema version table.
* `userid` : The ID of a dbms user who has permissions to select from the schema version table.
* `changeLogTableName` : The name of the changelog table to use.
* `delimiter` : Delimiter to use to separate scripts into statements, if dbdeploy will apply the scripts for you 
                i.e. you haven't specified outputfile. Default ;
* `delimiterType` : Either normal: split on delimiter wherever it occurs or row  only split
                    on delimiter if it features on a line by itself. Default normal.
* `lineEnding` : Line ending to separate indiviual statement lines when applying directly
                 to the database. Can be platform (the default line ending for the current platform),
                 cr, crlf or lf. Default platform.
* `lastChangeToApply` : The highest numbered delta script to apply.


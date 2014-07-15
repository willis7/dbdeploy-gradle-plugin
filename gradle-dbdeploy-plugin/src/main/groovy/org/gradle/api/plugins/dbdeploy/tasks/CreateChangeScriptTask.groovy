package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.scripts.ChangeScriptCreator
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory

/**
 * Gradle task for creating a new timestamped dbdeploy change script.
 * Task: changeScript
 * @author Sion Williams
 */
class CreateChangeScriptTask extends AbstractDbDeployTask {
    /**
     * Name suffix for the file that will be created (e.g. add_email_to_user_table).
     */
    @Input
    String nameSuffix = "new_change_script"


    CreateChangeScriptTask(){
        super ('Generate a new timestamped dbdeploy change script')
    }

    @Override
    void executeAction() {
        try {
            final ChangeScriptCreator changeScriptCreator = getConfiguredChangeScriptCreator()
            final File newChangeScript = changeScriptCreator.go()
            logger.info("Created new change script:\n\t" + newChangeScript.getAbsolutePath())

        } catch (Exception e) {
            logger.error(e)
            throw new GradleException("create change script failed", e)
        }
    }

    private ChangeScriptCreator getConfiguredChangeScriptCreator() {
        final ChangeScriptCreator changeScriptCreator = new ChangeScriptCreator()
        changeScriptCreator.setScriptDescription(getNameSuffix())
        changeScriptCreator.setScriptDirectory(getScriptdirectory())

        return changeScriptCreator
    }
}

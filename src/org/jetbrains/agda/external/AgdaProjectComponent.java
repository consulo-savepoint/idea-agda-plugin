package org.jetbrains.agda.external;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class AgdaProjectComponent implements ProjectComponent {
    private final boolean keepAlive = false;
    private final Project project;
    private AgdaProcess myProcess;

    public AgdaProjectComponent(Project project) {
        this.project = project;
    }

    public AgdaProcess getProcess() {
        return myProcess;
    }

    public void projectOpened() {
    }

    public void projectClosed() {
    }

    @NotNull
    public String getComponentName() {
        return "AgdaProjectComponent";
    }

    public void initComponent() {
        if (keepAlive) {
            try {
                myProcess = new AgdaProcess();
                myProcess.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void disposeComponent() {
        if (keepAlive) {
            myProcess.stop();
        }
    }

    public synchronized void execute(String cmd, AgdaProcess.Callback callback) throws IOException {
        if (keepAlive) {
            myProcess.execute(cmd, callback);
        } else {
            AgdaProcess process = new AgdaProcess();
            try {
                process.init();
            } catch (IOException e) {
                Notifications.Bus.notify(new Notification("Agda executable", "Agda execution problem",
                        "<html>Can't execute Agda, specify Agda executable path in settings.</html>",
                        NotificationType.ERROR));
                return;
            }
            process.execute(cmd, callback);
            process.stop();

        }
    }


    private static String esc(String path) {
        return path.replace("\\", "\\\\");
    }

    public List<AgdaExternalAnnotation> load(String path) {
        try {
            File agdaFile = new File(path);

            String cmd = "IOTCM \"" + esc(agdaFile.getPath()) + "\" Interactive Direct ( Cmd_load \"" + esc(agdaFile.getPath()) + "\" [\".\"] )\n";
            System.out.println(cmd);

            final AgdaOutputProcessor agdaOutputProcessor = new AgdaOutputProcessor();
            execute(cmd, new AgdaProcess.Callback() {

                @Override
                public boolean call(String command) {
                    return agdaOutputProcessor.processCmd(command);
                }
            });

            return agdaOutputProcessor.getMessages();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return null;

    }

}

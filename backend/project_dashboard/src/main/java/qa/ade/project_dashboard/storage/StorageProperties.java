package qa.ade.project_dashboard.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@ComponentScan("qa.ade.project_dashboard")
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = System.getProperty("java.io.tmpdir") + File.pathSeparatorChar + "upload-dir";

    public String getLocation() {
        File directory = new File(location);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

import Modules.IModule;
import Services.Service;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Collection;

public class FileAnalyzer {
    private static File file;
    private final Collection<IModule> modules;

    public FileAnalyzer(Collection<IModule> modules) {
        this.modules = modules;
    }

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        FileAnalyzer fileAnalyzer = ctx.getBean(FileAnalyzer.class);
        Service service = ctx.getBean(Service.class);

        String fileExtension = getFileExtension();

        for (IModule module : fileAnalyzer.modules) {
            if (module.checkFileExtension(fileExtension))
                service.chooseOperation(module, file);
        }
    }

    private static String getFileExtension() {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else if (file.isDirectory())
            return "directory";
        return "Unknown object";
    }

    public void setFile(String file) {
        FileAnalyzer.file = new File(file);
    }
}

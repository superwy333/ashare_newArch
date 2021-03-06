import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisGeneratorTool {

    public static void main(String[] args) {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String genCfg = "/config/generator.xml"; //src/*/resources的一级目录下  
        File configFile = new File(MyBatisGeneratorTool.class.getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        // MyBatisGeneratorProxy myBatisGenerator = null;
        MyBatisGenerator myBatisGenerator = null;
        List<String> list = config.getClassPathEntries();
        try {
            // myBatisGenerator = new MyBatisGeneratorProxy(config, callback, warnings);
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("begin generate......");
            myBatisGenerator.generate(null);
            System.out.println("end generate......");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

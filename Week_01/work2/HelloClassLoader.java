import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloClassLoader extends ClassLoader {
    private String definePath = System.getProperty("user.dir");

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class<?> newClass = (Class) helloClassLoader.loadClass("Hello");
        Object newInstance = newClass.newInstance();
        System.out.println("Class Hello be initialized");
        Method helloMethod = newClass.getMethod("hello");
        helloMethod.setAccessible(true);
        helloMethod.invoke(newInstance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> newClass = null;
        try {
            byte[] fileBytes = LoadFileBytes(name);
            newClass = defineClass(name,fileBytes,0,fileBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newClass;
    }
    // find xclass
    private byte[] LoadFileBytes(String name) throws IOException {
        String fileName = definePath + '/' + name + ".xlass";
        byte[] fileBytes = Files.readAllBytes(Paths.get(fileName));
        for(int i = 0; i<fileBytes.length; i++) {
            fileBytes[i] = (byte)(255 - fileBytes[i]);
        }
        //System.out.println(fileBytes.length);
        return fileBytes;
    }
}

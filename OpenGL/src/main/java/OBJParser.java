import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class OBJParser {
    static Obj obj;

    static FloatBuffer vertices;
    static FloatBuffer texCoords;
    static FloatBuffer normals;

    public static void main(String[] args) {
        try {
            // Print the file path to the console
            String cwd =  System.getProperty("user.dir");
            System.out.println(cwd);
            File objFile = new File(cwd+ "/src/main/resources/objs/cube.obj");
            InputStream objInputStream = new FileInputStream(objFile);
            obj = ObjUtils.convertToRenderable(
                    ObjReader.read(objInputStream));
            IntBuffer indices = ObjData.getFaceVertexIndices(obj);
            vertices = ObjData.getVertices(obj);
            texCoords = ObjData.getTexCoords(obj, 2);
            normals = ObjData.getNormals(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < vertices.capacity(); i++) {
            System.out.println(vertices.get(i));
        }

    }
}
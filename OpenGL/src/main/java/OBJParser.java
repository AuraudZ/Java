import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;

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
            InputStream objInputStream = new FileInputStream("C:\\Users\\aubte\\Desktop\\cube.obj");
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
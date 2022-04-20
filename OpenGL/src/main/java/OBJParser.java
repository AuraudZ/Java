import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OBJParser {

    private List<Vertex3> vertices = new ArrayList<>();


    static class Face {
        int[] v;

        Face(int[] v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return "Face{" +
                    "v=" + Arrays.toString(v) +
                    '}';
        }
    }

    public OBJ parse(String filename) {
        OBJ obj = new OBJ();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                String[] tokens = line.split("[ ]+");
                if (tokens.length == 0) {
                    continue;
                }
                String command = tokens[0];

                Vertex3 vertex;
                switch (command) {
                    case "o" -> obj.name = tokens[1];
                    case "v" -> obj.vertices.add(parseVert(tokens));
                    case "vn" -> obj.normals = Arrays.copyOfRange(tokens, 1, tokens.length);
                    case "f" -> obj.faces = Arrays.copyOfRange(tokens, 1, tokens.length);
                    case "g" -> obj.group = tokens[1];
                    case "usemtl" -> obj.material = tokens[1];
                    case "s" -> obj.smooth = tokens[1];
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }


    private Vertex3 parseVert(String[] tokens) {
        return Arrays.stream(Arrays.stream(tokens).skip(1).mapToDouble(Float::parseFloat).toArray()).mapToObj(Vertex3::new).reduce(new Vertex3(), (a, b) -> {
                a.x += b.x;
                a.y += b.y;
                a.z += b.z;
                return a;
            });
    }

    public static void main(String[] args) {
        OBJParser parser = new OBJParser();
        OBJ obj = parser.parse("C:\\Users\\aubte\\Desktop\\Cube.obj");
        System.out.println(obj.name);
        System.out.println(obj.vertices.get(7));
        // System.out.println(Arrays.toString(obj.vertices));

    }


}

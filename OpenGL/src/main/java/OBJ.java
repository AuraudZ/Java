import java.util.ArrayList;
import java.util.List;

public class OBJ {
    public String name;
    public String material;
    public String group;
    public String smooth;
    public List<Vertex3> vertices;
    public List<Vertex3> normals;
    public String faces;


    public OBJ() {
        this.name = "";
        this.material = "";
        this.group = "";
        this.smooth = "";
        this.vertices = new ArrayList<Vertex3>();
        this.normals = new ArrayList<Vertex3>();
        this.faces = String.valueOf(new ArrayList<OBJParser.Face>());
    }


}




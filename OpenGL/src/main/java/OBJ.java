import java.util.ArrayList;
import java.util.List;

public class OBJ {
    public String name;
    public String material;
    public String group;
    public String smooth;
    public List<Vertex3> vertices;
    public String[] normals;
    public String[] faces;


    public OBJ() {
        this.name = "";
        this.material = "";
        this.group = "";
        this.smooth = "";
        this.vertices = new ArrayList<Vertex3>();
        this.normals = new String[0];
        this.faces = new String[0];
    }


}




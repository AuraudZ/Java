import java.lang.Math;

public class RenderFrame {

    static double theta_spacing = 0.07;
    static double phi_spacing = 0.02;
    static double screen_height = 100;
    static double screen_width = 100;
    static double R1 = 1;
    static double R2 = 2;
    static double K2 = 5;
    static double K1 = screen_width * K2 * 3 / (8 * (R1 + R2));

    public static void main(String[] args) {
        renderFrame(0.2, 0.5);
    }

    public static void renderFrame(double A, double B) {
        double cosA = (double) Math.cos(A);
        double cosB = (double) Math.cos(B);
        double sinA = (double) Math.sin(A);
        double sinB = (double) Math.sin(B);
        screen_height = 100;
        screen_width = 100;
        // output buffer array from 0 to screen_width , 0 to screen_height
        char[][] output = new char[(int) screen_width][(int) screen_height];
        // initialize output buffer with just empty spaces
        output = initializeOutput(output);
        // zbuffer 2d array
        double[][] zbuffer = new double[(int) screen_height][(int) screen_width];
        zbuffer = initializeZBuffer(zbuffer);
        for (double theta = 0; theta < 2 * Math.PI; theta += theta_spacing) {
            double costheta = Math.cos(theta), sintheta = Math.sin(theta);
            for (double phi = 0; phi < 2 * Math.PI; phi += phi_spacing) {
                double cosphi = Math.cos(phi), sinphi = Math.sin(phi);
                double circlex = R2 + R1 * costheta;
                double circley = R1 * sintheta;
                double x = circlex * (cosB * cosphi + sinA * sinB * sinphi) - circley * cosA * sinB;
                double y = circlex * (sinB * cosphi - sinA * cosB * sinphi) + circley * cosA * cosB;
                double z = K2 + cosA * circlex * sinphi + circley * sinA;
                double ooz = 1 / z; // "one over z"
                int xp = (int) (screen_width / 2 + K1 * ooz * x);
                int yp = (int) (screen_height / 2 - K1 * ooz * y);
                double L = cosphi * costheta * sinB - cosA * costheta * sinphi - sinA * sintheta
                        + cosB * (cosA * sintheta - costheta * sinA * sinphi);
                if (L > 0) {
                    if (ooz > zbuffer[xp][yp]) {
                        zbuffer[xp][yp] = ooz;
                        int luminance_index = (int) L * 8;
                        String luminance = ".,-~:;=!*#$@";
                        for (int i = 0; i < luminance_index; i++) {
                            for (int j = 0; j < luminance_index; j++) {
                                output[xp + i][yp + j] = luminance.charAt(i);
                            }
                        }
                    }

                }
                System.out.print("\033[H\033[2J");
                for (int i = 0; i < screen_width; i++) {
                    for (int j = 0; j < screen_height; j++) {
                        System.out.print(output[i][j]);
                    }
                    System.out.println();
                }
            }
        }

    }

    private static char[][] initializeOutput(char[][] output) {
        for (int i = 0; i < screen_width; i++) {
            for (int j = 0; j < screen_height; j++) {
                output[i][j] = ' ';
            }
        }
        return output;
    }

    private static double[][] initializeZBuffer(double[][] zbuffer) {
        for (int i = 0; i < screen_width; i++) {
            for (int j = 0; j < screen_height; j++) {
                zbuffer[i][j] = 0;
            }
        }
        return zbuffer;
    }
}

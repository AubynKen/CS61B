public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int arrayLength = in.readInt();
        in.readDouble();
        Body[] res = new Body[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = "images/" +in.readString();
            res[i] = new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return res;
    }

    public static void main(String[] args) {
//        double T = Double.parseDouble(args[0]);
//        double dt = Double.parseDouble(args[1]);
//        String fileName = args[2];
        double T = 157788000.0;
        double dt = 25000.0;
        String fileName = "planets.txt";
        double Radius = readRadius(fileName);
        Body[] bodies = readBodies(fileName);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-Radius, Radius);
        StdDraw.clear();

        for (double t = 0; t < T; t+= dt) {
            double[] xxForces = new double[bodies.length];
            double[] yyForces = new double[bodies.length];
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < bodies.length; i++) {
                xxForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yyForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xxForces[i], yyForces[i]);
            }
            for (Body body : bodies) {
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

//        StdDraw.picture(0, 0, "images/starfield.jpg");
//
//        StdDraw.picture(0,0, "image/startfield.jpg");
//        for (Body body : bodies) {
//            body.draw();
//        }

        StdDraw.show();
        StdDraw.pause(2000);
    }

}

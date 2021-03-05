public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body b) {
        if (this.equals(b)) return 0;
        double distance = this.calcDistance(b);
        return (G * this.mass * b.mass) / (distance * distance);
    }

    public double calcForceExertedByX(Body b) {
        if (this.equals(b)) return 0;
        double F = calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        double distance = calcDistance(b);
        return F * dx / distance;
    }

    public double calcForceExertedByY(Body b) {
        if (this.equals(b)) return 0;
        double F = calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        double distance = calcDistance(b);
        return F * dy / distance;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double res = 0;
        for (Body b : bodies) {
            res += this.calcForceExertedByX(b);
        }
        return res;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double res = 0;
        for (Body b : bodies) {
            res += this.calcForceExertedByY(b);
        }
        return res;
    }

    public void update(double dt, double xxForce, double yyForce) {
        double xxAcc = xxForce / mass;
        double yyAcc = yyForce / mass;
        this.xxVel += dt * xxAcc;
        this.yyVel += dt * yyAcc;
        this.xxPos += dt * xxVel;
        this.yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }

}

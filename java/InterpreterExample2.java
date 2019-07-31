package boomerang.example;

public class InterpreterExample2 {

    public static void main(String... args) {
        FflatExpr boo1 = new FBoolExpr(false);
        FflatExpr boo2 = new FBoolExpr(true);
        FflatExpr boo3 = new FBoolExpr(false);
        FflatExpr boo4 = new FBoolExpr(true);
        FflatExpr andExpr = new FAnd(boo1, boo2);
        FflatExpr orAndExpr = new FOr(andExpr, andExpr);
        FflatExpr trueOrFalse = new FOr(boo4, boo3);
//        FflatExpr tOrFOrT = new FOr(trueOrFalse, boo4);
        FflatExpr evalRes = orAndExpr.expand();
        FflatExpr evalRes2 = orAndExpr.evaluate();
        queryFor(evalRes);
        System.out.println(evalRes2.getValue());
    }

    public enum AstNodeType {
        BOOL,
        OR,
        AND;
    }


    private static abstract class FflatExpr {
        public abstract AstNodeType getNodeType();
        public abstract Boolean getValue();
        public abstract FflatExpr getLeft();
        public abstract FflatExpr getRight();
        public abstract FflatExpr evaluate();
        public abstract FflatExpr expand();

    }

    private static class FAnd extends FflatExpr {
        private FflatExpr bExpr1;
        private FflatExpr bExpr2;

        public FAnd (FflatExpr b1, FflatExpr b2) {
            this.bExpr1 = b1;
            this.bExpr2 = b2;
        }

        public AstNodeType getNodeType ()
        {
            return AstNodeType.AND;
        }

        @Override
        public Boolean getValue () {
            return null;
        }

        @Override
        public FflatExpr getLeft() {
            return bExpr1;
        }

        @Override
        public FflatExpr getRight() {
            return bExpr2;
        }

        @Override
        public FflatExpr evaluate() {
            FflatExpr bval1 =  bExpr1.evaluate();
            FflatExpr bval2 =  bExpr2.evaluate();
            if (!bval1.getValue()) {
                return bval1;
            }
            else {
                return bval2;
            }
        }

        @Override
        public FflatExpr expand() {
            FflatExpr res = new FAnd(bExpr1, bExpr2);
            return res;
        }
    }

    private static class FOr extends FflatExpr {
        private FflatExpr bExpr1;
        private FflatExpr bExpr2;

        public FOr (FflatExpr b1, FflatExpr b2) {
            this.bExpr1 = b1;
            this.bExpr2 = b2;
        }

        public AstNodeType getNodeType () {
            return AstNodeType.OR;
        }

        @Override
        public Boolean getValue () {
            return null;
        }

        @Override
        public FflatExpr getLeft() {
            return bExpr1;
        }

        @Override
        public FflatExpr getRight() {
            return bExpr2;
        }

        @Override
        public FflatExpr evaluate() {
            FflatExpr bval1 =  bExpr1.evaluate();
            FflatExpr bval2 =  bExpr2.evaluate();
            if (bval1.getValue()) {
                return bval1;
            }
            else {
                return bval2;
            }
        }

        @Override
        public FflatExpr expand() {
            FflatExpr res = new FOr(bExpr1, bExpr2);
            return res;
        }
    }

    private static class FBoolExpr extends FflatExpr {

        private boolean value;

        public FBoolExpr(boolean value) {
            this.value = value;
        }

        public AstNodeType getNodeType() {
            return AstNodeType.BOOL;
        }

        @Override
        public Boolean getValue() {
            return value;
        }

        @Override
        public FflatExpr getLeft() {
            return null;
        }

        @Override
        public FflatExpr getRight() {
            return null;
        }

        @Override
        public FBoolExpr evaluate() {
            return this;
        }

        @Override
        public FflatExpr expand() {
            FflatExpr res = new FAnd(new FOr(this, this), this);
            return res;
        }

    }

    private static void queryFor(FflatExpr query) {

    }

}
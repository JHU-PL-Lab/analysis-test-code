package boomerang.example;

public class DynamicAST2 {

    public static void main(String... args) {
        FflatExpr tru = new FBoolExpr(true);
        FflatExpr fal = new FBoolExpr(false);
        FflatExpr test = makeAST(2, tru, fal);
        FflatExpr evalRes2 = test.evaluate();
        queryFor(evalRes2);
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

    }


    public static FflatExpr makeAST(Integer num, FflatExpr tru, FflatExpr fal) {

        if (num == 0) {
            return tru;
        }
        else if (num == 1) {
            return fal;
        }
        else if (num % 3 == 1) {
            FflatExpr simpleExpr = makeAST(num/3, tru, fal);
            FflatExpr result = new FAnd(simpleExpr, simpleExpr);
            return result;
        }
        else if (num % 3 == 2) {
            FflatExpr leftExpr = makeAST(num/3, tru, fal);
//            boolean p1 = ((BoolExpr) leftExpr.visit(null, new Evaluate())).getValue();
//            System.out.println(p1);
            FflatExpr rightExpr = makeAST(num/3 + 1, tru, fal);
//            boolean p2 = ((BoolExpr) rightExpr.visit(null, new Evaluate())).getValue();
//            System.out.println(p2);
            FflatExpr result = new FAnd(leftExpr, rightExpr);
            return result;
        }
        else {
            FflatExpr leftExpr = makeAST(num/3, tru, fal);
            FflatExpr rightExpr = makeAST(num/3 + 1, tru, fal);
            FflatExpr result = new FOr(leftExpr, rightExpr);
            return result;
        }

    }

    private static void queryFor(FflatExpr query) {

    }

}
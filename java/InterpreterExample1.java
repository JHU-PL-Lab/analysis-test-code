package boomerang.example;

public class InterpreterExample1 {

    public static void main(String... args) {
        FflatExpr boo1 = new FBool(false);
        FflatExpr boo2 = new FBool(true);
        FflatExpr andExpr = new FAnd(boo1, boo2);
        FflatExpr orAndExpr = new FOr(andExpr, andExpr);
        FflatExpr andOrAndExpr = new FAnd (orAndExpr, orAndExpr);
        FflatExpr evalRes2 = evaluate(orAndExpr);
        Boolean result_val = evalRes2.getValue();
        queryFor(evalRes2);
        System.out.println(result_val);
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
    }

    private static class FBool extends FflatExpr {

        private boolean value;

        public FBool(boolean value) {
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
    }
    
    public static FflatExpr evaluate (FflatExpr expr) {

        AstNodeType nodeType = expr.getNodeType();

        switch (nodeType) {
            case BOOL:
                return expr;

            case AND:
                FflatExpr andExpr1 = evaluate(expr.getLeft());
                FflatExpr andExpr2 = evaluate(expr.getRight());

                Boolean e1Val = andExpr1.getValue();

                if (e1Val) {
                    return andExpr2;
                }
                else {
                    return andExpr1;
                }

            case OR:
                FflatExpr orExpr1 = evaluate(expr.getLeft());
                FflatExpr orExpr2 = evaluate(expr.getRight());

                Boolean exp1Val = orExpr1.getValue();

                if (exp1Val) {
                    return orExpr1;
                }
                else {
                    return orExpr2;
                }
        }
        return null;
    }

    private static void queryFor(FflatExpr query) {

    }

}

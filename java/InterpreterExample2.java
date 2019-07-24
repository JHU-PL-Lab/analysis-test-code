package boomerang.example;

public class InterpreterExample2 {

    public static void main(String... args) {
        FflatExpr boo1 = new FBoolExpr(false);
        FflatExpr boo2 = new FBoolExpr(true);
        FflatExpr andExpr = new FAnd(boo1, boo2);
        FflatExpr orAndExpr = new FOr(andExpr, andExpr);
        FflatExpr andOrAndExpr = new FAnd(orAndExpr, orAndExpr);
        FflatExpr evalRes2 = orAndExpr.evaluate();
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


//	private static class FInt extends FflatExpr {
//
//		private int value;
//
//		public FInt(int value) {
//			this.value = value;
//		}
//
//		public String getNodeType () {
//			return "INT";
//		}
//
//		@Override
//		public int getValue () {
//			return value;
//		}
//
//		@Override
//		public <T> T getLeft() {
//			return null;
//		}
//
//		@Override
//		public <T> T getRight() {
//			return null;
//		}
//
//	}
//
//    public static FflatExpr evaluate (FflatExpr expr) {
//
//        AstNodeType nodeType = expr.getNodeType();
//
//        switch (nodeType) {
//            case BOOL:
//                return expr;
//
//            case AND:
//                FflatExpr andExpr1 = evaluate(expr.getLeft());
//                FflatExpr andExpr2 = evaluate(expr.getRight());
////                String node1Type = andExpr1.getNodeType();
////                String node2Type = andExpr2.getNodeType();
//                Boolean e1Val = andExpr1.getValue();
//                Boolean e2Val = andExpr1.getValue();
//                Boolean res = e1Val && e2Val;
//                FflatExpr resExpr = new FBoolExpr(res);
//                return resExpr;
//
//            case OR:
//                FflatExpr orExpr1 = evaluate(expr.getLeft());
//                FflatExpr orExpr2 = evaluate(expr.getRight());
////                String node1orType = orExpr1.getNodeType();
////                String node2orType = orExpr2.getNodeType();
//                Boolean exp1Val = orExpr1.getValue();
//                Boolean exp2Val = orExpr1.getValue();
//                Boolean result = exp1Val || exp2Val;
//                FflatExpr resultExpr = new FBoolExpr(result);
//                return resultExpr;
//        }
//        return null;
//    }

    private static void queryFor(FflatExpr query) {

    }

}
package boomerang.example;

public class InterpreterExample4 {

    public static void main(String... args) {
        FflatExpr boo1 = new FBool(false);
        FflatExpr boo2 = new FBool(true);
        FflatExpr andExpr = new FAnd(boo1, boo2);
        FflatExpr orAndExpr = new FOr(andExpr, andExpr);
        FflatExpr andOrAndExpr = new FAnd (orAndExpr, orAndExpr);
//        FflatExpr evalRes = evaluate(andOrAndExpr);
        FflatExpr evalRes2 = evaluate(orAndExpr);
        Boolean result_val = evalRes2.getValue();
        queryFor(evalRes2);
        System.out.println(result_val);
    }

    private static abstract class FflatExpr {
        public abstract String getNodeType();
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

        public String getNodeType () {
            return "AND";
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

        public String getNodeType () {
            return "OR";
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

        public String getNodeType() {
            return "BOOL";
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
//	}
//
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

    public static FflatExpr evaluate (FflatExpr expr) {

        String nodeType = expr.getNodeType();

        switch (nodeType) {
            case "BOOL":
                return expr;

            case "AND":
                FflatExpr andExpr1 = evaluate(expr.getLeft());
                FflatExpr andExpr2 = evaluate(expr.getRight());
//                String node1Type = andExpr1.getNodeType();
//                String node2Type = andExpr2.getNodeType();
                Boolean e1Val = andExpr1.getValue();
                Boolean e2Val = andExpr1.getValue();
                Boolean res = e1Val && e2Val;
                FflatExpr resExpr = new FBool(res);
                return resExpr;

            case "OR":
                FflatExpr orExpr1 = evaluate(expr.getLeft());
                FflatExpr orExpr2 = evaluate(expr.getRight());
//                String node1orType = orExpr1.getNodeType();
//                String node2orType = orExpr2.getNodeType();
                Boolean exp1Val = orExpr1.getValue();
                Boolean exp2Val = orExpr1.getValue();
                Boolean result = exp1Val || exp2Val;
                FflatExpr resultExpr = new FBool(result);
                return resultExpr;
        }
        return null;
    }

    private static void queryFor(FflatExpr query) {

    }

}
package boomerang.example;

public class InterpreterExample3_4NT {

    public static void main(String... args) {
        Expr fal = new BoolExpr(false);
        Expr tru = new BoolExpr(true);
        Expr trueAndFalse = new OrExpr(fal, tru);
        Expr tAndfAndT = new AndExpr(trueAndFalse, tru);
        Evaluate eval = new Evaluate();
        CountNodes countNodes = new CountNodes();
        Object res = tAndfAndT.visit(null, eval);
        Object res_p = tAndfAndT.visit(null, countNodes);

        System.out.println(res_p);
        queryFor(res);

    }


    public interface ExprVisitor<P, R> {
        public R visitBool(BoolExpr e, P p);
        public R visitOr(OrExpr e, P p);
        public R visitAnd(AndExpr e, P p);
    }

    public static class Evaluate implements ExprVisitor<Void, Expr> {
        public Expr visitBool (BoolExpr e, Void v) {
            return e;
        }
        public Expr visitOr (OrExpr e, Void v) {
            Expr leftExp = e.get_left();
            Expr rightExp = e.get_right();
            BoolExpr evalLeft = (BoolExpr) leftExp.visit(null, this);
            BoolExpr evalRight = (BoolExpr) rightExp.visit(null, this);
            if (evalLeft.getValue()) {
                return evalLeft;
            }
            else {
                return evalRight;
            }
        }
        public Expr visitAnd (AndExpr e, Void v) {
            Expr leftExp = e.get_left();
            Expr rightExp = e.get_right();
            BoolExpr evalLeft = (BoolExpr) leftExp.visit(null, this);
            BoolExpr evalRight = (BoolExpr) rightExp.visit(null, this);
            if (!evalLeft.getValue()) {
                return evalLeft;
            }
            else {
                return evalRight;
            }
        }
    }

    public static class CountNodes implements ExprVisitor<Void, Integer> {
        public Integer visitBool (BoolExpr e, Void v) {
            return new Integer(1);
        }
        public Integer visitOr (OrExpr e, Void v) {
            Expr leftExp = e.get_left();
            Expr rightExp = e.get_right();
            Integer evalLeft = (Integer) leftExp.visit(null, this);
            Integer evalRight = (Integer) rightExp.visit(null, this);
            Integer res = new Integer (evalLeft + evalRight);
            return res;
        }
        public Integer visitAnd (AndExpr e, Void v) {
            Expr leftExp = e.get_left();
            Expr rightExp = e.get_right();
            Integer evalLeft = (Integer)leftExp.visit(null, this);
            Integer evalRight = (Integer) rightExp.visit(null, this);
            Integer res = new Integer (evalLeft + evalRight);
            return res;
        }
    }


    public static abstract class Expr {

        public abstract <P, R> R visit(P p, ExprVisitor<P, R> v);

    }


    public static class BoolExpr extends Expr {

        private boolean value;

        public boolean getValue() {
            return value;
        }

        public BoolExpr(boolean val){ this.value = val;}

        @Override
        public <P, R> R visit(P p, ExprVisitor<P, R> v) {
            return v.visitBool(this, p);
        }
    }

    public static class OrExpr extends Expr {

        private Expr left_expr;
        private Expr right_expr;

        public OrExpr(Expr b1, Expr b2) {
            this.left_expr = b1;
            this.right_expr = b2;
        };

        public Expr get_left() {
            return left_expr;
        }

        public Expr get_right() {
            return right_expr;
        }

        @Override
        public <P, R> R visit(P p, ExprVisitor<P, R> v) {
            return v.visitOr(this, p);
        }
    }

    public static class AndExpr extends Expr {

        Expr left_expr;
        Expr right_expr;

        public AndExpr(Expr b1, Expr b2){
            this.left_expr = b1;
            this.right_expr = b2;
        };


        public Expr get_left() {
            return left_expr;
        }

        public Expr get_right() {
            return right_expr;
        }

        @Override
        public <P, R> R visit(P p, ExprVisitor<P, R> v) {
            return v.visitAnd(this, p);
        }
    }

    private static <T> void queryFor(T query) {

    }

}
package boomerang.example;

import fj.test.Bool;

public class DynamicAST1 {

    public static void main(String... args) {

        BoolExpr tru = new BoolExpr(true);
        BoolExpr fal = new BoolExpr(false);
        Expr test = makeAST(2, tru, fal);
        BoolExpr res = (BoolExpr) test.visit(null, new Evaluate());
        System.out.println(res.getValue());
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

    public static Expr makeAST(Integer num, BoolExpr tru, BoolExpr fal) {

        if (num == 0) {
            return tru;
        }
        else if (num == 1) {
            return fal;
        }
        else if (num % 3 == 1) {
            Expr simpleExpr = makeAST(num/3, tru, fal);
            Expr result = new AndExpr(simpleExpr, simpleExpr);
            return result;
        }
        else if (num % 3 == 2) {
            Expr leftExpr = makeAST(num/3, tru, fal);
//            boolean p1 = ((BoolExpr) leftExpr.visit(null, new Evaluate())).getValue();
//            System.out.println(p1);
            Expr rightExpr = makeAST(num/3 + 1, tru, fal);
//            boolean p2 = ((BoolExpr) rightExpr.visit(null, new Evaluate())).getValue();
//            System.out.println(p2);
            Expr result = new AndExpr(leftExpr, rightExpr);
            return result;
        }
        else {
            Expr leftExpr = makeAST(num/3, tru, fal);
            Expr rightExpr = makeAST(num/3 + 1, tru, fal);
            Expr result = new OrExpr(leftExpr, rightExpr);
            return result;
        }

    }

    private static void queryFor(Expr query) {

    }

}

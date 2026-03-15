class Fancy {
    static final long MOD= 1000000007;
    List<Long> arr;
    long add;
    long mul;


    public Fancy() {
        arr = new ArrayList<>();
        
        add = 0; 
        mul = 1;    
    }
    private long modInverse(long x){
        return pw(x, MOD - 2);
    }
    public void append(int val) {
        long x = (val - add + MOD) % MOD;
        x = (x * modInverse(mul)) % MOD;
        arr.add(x);
    }
    
    public void addAll(int inc) {
        add = (add+ inc) % MOD;
    }
    
    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add= (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if(idx >= arr.size())
        return -1;
        long val = arr.get(idx);
        val = (val * mul  + add) % MOD;
        return (int) val;
    }
    private long pw(long x, long y){
        long res = 1;
        while (y > 0){
            if((y & 1) == 1)
            res = (res * x) % MOD;
            x = (x * x) % MOD;
            y >>= 1;
        }
        return res;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
class Robot {
        int w , h ;
        int x = 0,  y = 0;
        int dir = 0;
        int[][] dirs = {
         {1,0}, {0,1}, {-1,0}, {0,-1} 
        };
        String[]  dirNames = {"East", "North", "West", "South"};
        int perimeter;
        public Robot(int width, int height){
            this.w = width;
            this.h = height;
            this.perimeter = 2 * (w + h - 2);
        }

    
    public void step(int num) {
        num %= perimeter;
        if(num == 0 && (x == 0 && y == 0)){
            dir = 3;
            return ;
        }
        while(num --> 0){
            int nx = x + dirs[dir][0];
            int ny = y + dirs[dir][1];

            if(nx < 0 || nx >= w || ny < 0 || ny >= h){
                dir = (dir + 1) % 4;
                nx = x + dirs[dir][0];
                ny = y + dirs[dir][1];
            }
            x = nx;
            y = ny;
        }
        
    }
    
    public int[] getPos() {
        return new int[]{x , y};
        
    }
    
    public String getDir() {
        return dirNames[dir];
        
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
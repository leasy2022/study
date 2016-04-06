package MyTest;

import java.util.Random;

/**
 * Created by wushang on 15/12/4.
 */
/*
/**
*  使用小顶堆求解
*  AMD 4400+,Win7 64 ,4G RAM,JDK1.6_update24
*  求1亿随机数TopN，耗时1.4S
*  http://www.oschina.net/code/snippet_572971_18377
*/
public class TopN {
    private int[] arr=null;
    private int top;
    private int setCount=0;
    private Random random;
    public TopN(){
        this(100);
    }

    public TopN(int top){
        this.top =top;
        init();
    }

    private void init(){
        arr = new int[top];
        random = new Random();
        for(int i=0;i<top;i++){
            arr[i] = Integer.MIN_VALUE;
        }
    }

    public boolean compare(int index,int value){
        if(arr[index]<value){
            return true;
        }else{
            return false;
        }
    }

    public void setValue(int index,int value){
        this.arr[index] =value;
    }

    public void setInMax(int value){
        this.arr[setCount++] =value;
        if(setCount == top){
            this.heap();
        }
    }

    public boolean isMaxSetCount(){
        return setCount>=top;
    }

    public int getNextInt(){
        int i = this.random.nextInt();
        return i;
    }

    public void heap(){
        int length =arr.length;
        int t= (length-1)>>1;
        for(int i=t;i>=0;i--){
            minHeapify(arr,i,length);
        }
    }

    private void minHeapify(int[] array,int i,int size){
        int l = ((i<<1)+1);
        int r = l+1;
        int min = i;
        if(l<size && this.compare(l, arr[min])){
            min = l;
        }
        if(r<size && this.compare(r, arr[min])){
            min = r;
        }
        if(min != i){
            swap(array,min,i);
            if(min<size-1){
                minHeapify(array, min, size);
            }
        }
    }

    private void swap(int[] array,int i,int j){
        array[i]^=array[j];
        array[j]^=array[i];
        array[i]^=array[j];
    }

    public void print(){
        int j=0;
        for(int i : arr){
            ++j;
            System.out.print(i+",");
            if(j>=10){
                System.out.println();
                j=0;
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        long start =System.nanoTime();
        TopN t100 = new TopN(100);
        for(long i=0;i<100000000;i++){
            int tmp = t100.getNextInt();
            //如果没有超出100个
            if(!t100.isMaxSetCount()){
                t100.setInMax(tmp);
            }else if(t100.compare(0, tmp)){
                t100.setValue(0, tmp);
                t100.heap();
            }
        }
        long end = System.nanoTime();
        t100.print();
        System.out.println("Used time:"+(end-start)/1000000+" ms");
    }
}

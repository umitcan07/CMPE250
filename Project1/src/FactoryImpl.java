import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class FactoryImpl implements Factory {

    private Holder first;
    private Holder last;
    private Integer size = 0;

    public boolean isEmpty() {

        return this.size == 0;

    }

    public Integer getSize() {

        return this.size;

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("{");
        Holder temp = this.first;
        if (temp != null) {
            res.append(temp.getProduct().toString());
        }
        for (int i = 1; i < this.size; i++) {
            assert temp != null;
            if (temp.getNextHolder() != null) {
                res.append(",");
                res.append(temp.getNextHolder().getProduct().toString());
                temp = temp.getNextHolder();

            }
        }
        res.append("}");
        return res.toString();
    }

    @Override
    public void addFirst(Product product) {

        Holder temp = new Holder(null, product, null);
        if (isEmpty()) {
            last = temp;
            first = temp;
            size++;
        } else {
            first.setPreviousHolder(temp);
            temp.setNextHolder(first);
            first = temp;
            size++;
        }
    }

    @Override
    public void addLast(Product product) {

        Holder temp = new Holder(null, product, null);
        if (isEmpty()) {
            first = temp;
            last = temp;
            size++;
        } else {
            last.setNextHolder(temp);
            temp.setPreviousHolder(last);
            last = temp;
            size++;
        }

    }

    @Override
    public Product removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Factory is empty.");
        }
        Product p = first.getProduct();
        if (getSize() == 1) {
            first = null;
            last = null;
        } else {
            first = first.getNextHolder();
            first.setPreviousHolder(null);
        }
        size--;
        return p;
    }

    @Override
    public Product removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Factory is empty.");
        }
        Product p = last.getProduct();
        if (getSize() == 1) {
            first = null;
            last = null;
        } else {
            last = last.getPreviousHolder();
            last.setNextHolder(null);
        }
        size--;
        return p;
    }

    @Override
    public Product find(int id) throws NoSuchElementException {
        Holder tmp = first; // to iterate from the start.
        while (tmp != null) {
            if (tmp.getProduct().getId() == id) {
                return tmp.getProduct();
            }
            tmp = tmp.getNextHolder();

        }
        throw new NoSuchElementException("Product not found.");
    }

    @Override
    public Product update(int id, Integer value) throws NoSuchElementException {

        Holder tmp = first; //to iterate from the start
        while (tmp != null) {
            if (tmp.getProduct().getId() == id) {
                Product oldProd = new Product(tmp.getProduct().getId(), tmp.getProduct().getValue()); // not to lose the product that is being returned
                tmp.getProduct().setValue(value);
                return oldProd;
            }
            tmp = tmp.getNextHolder();

        }
        throw new NoSuchElementException("Product not found.");
    }

    @Override
    public Product get(int index) throws IndexOutOfBoundsException {
        if (index >= getSize() || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if (index == 0) {
            return first.getProduct();
        }
        if (index == getSize() - 1) {
            return last.getProduct();
        }
        Holder tmp = first;

        for (int i = 0; i < index; i++) {
            tmp = tmp.getNextHolder();
        }
        return tmp.getProduct();

    }

    @Override
    public void add(int index, Product product) throws IndexOutOfBoundsException {
        if (index > getSize() || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        } else {
            if (index == 0) {
                addFirst(product);
            } else if (index == getSize()) {
                addLast(product);
            } else {
                Holder tmp = first;
                for (int i = 0; i < index - 1; i++) {
                    tmp = tmp.getNextHolder();
                }
                Holder newHolder = new Holder(null, product, null);
                tmp.getNextHolder().setPreviousHolder(newHolder);
                newHolder.setPreviousHolder(tmp);
                newHolder.setNextHolder(tmp.getNextHolder());
                tmp.setNextHolder(newHolder);
                size++;
            }
        }
    }

    @Override
    public Product removeIndex(int index) throws IndexOutOfBoundsException {
        if(getSize() == 0) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if(index >= getSize() || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if(index == 0) {
            return removeFirst();
        }
        if(index == getSize()-1){
            return removeLast();
        }
        Holder tmp = first;
        for(int i = 0; i<index; i++) {
            tmp = tmp.getNextHolder();
        }
        tmp.getNextHolder().setPreviousHolder(tmp.getPreviousHolder());
        tmp.getPreviousHolder().setNextHolder(tmp.getNextHolder());
        size--;
        return tmp.getProduct();
    }

    @Override
    public Product removeProduct(int value) throws NoSuchElementException {
        Holder tmp = first;
        while(tmp != null){
            if(tmp.getProduct().getValue() == value) {

                if(tmp == first) {
                    return removeFirst();
                }

                if(tmp == last) {
                    return removeLast();
                }

                tmp.getNextHolder().setPreviousHolder(tmp.getPreviousHolder());
                tmp.getPreviousHolder().setNextHolder(tmp.getNextHolder());
                size--;
                return tmp.getProduct();

            }
            tmp = tmp.getNextHolder();
        }
        throw new NoSuchElementException("Product not found.");
    }

    @Override
    public int filterDuplicates() {
        if(getSize()==0 || getSize()==1) {
            return 0;
        }
        int counter = 0;
        Holder tmp = first;
        HashSet<Integer> mySet = new HashSet<>();

        while(tmp != null) {
            if(mySet.contains(tmp.getProduct().getValue())) {
                if(tmp == last) {
                    tmp.getPreviousHolder().setNextHolder(null);
                    last = tmp.getPreviousHolder();
                    size--;
                    counter++;
                    return counter;
                } else {
                    tmp.getPreviousHolder().setNextHolder(tmp.getNextHolder());
                    tmp.getNextHolder().setPreviousHolder(tmp.getPreviousHolder());
                    size--;
                    counter++;
                }

            } else {
                mySet.add(tmp.getProduct().getValue());
            }
            tmp = tmp.getNextHolder();
        }
        return counter;
    }

    @Override
    public void reverse() {
        if(getSize() < 2) {
            return;
        }

        Holder iterator = first; // Changing the links starting from head
        while(iterator != null) {
            Holder nxt = iterator.getNextHolder(); // Keep the next and previous holders no to lose reference.
            Holder prv = iterator.getPreviousHolder();
            iterator.setNextHolder(prv);
            iterator.setPreviousHolder(nxt);
            iterator = nxt; // To continue the while loop (iteration)
        }

        Holder old_first = first; // Temp variable to interchange head and tail
        Holder old_last = last;
        first = old_last;
        last = old_first;

    }
}
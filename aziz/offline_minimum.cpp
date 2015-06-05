#include <iostream> // cout
#include <vector> //vector
#include <numeric> //iota

using namespace std;

int find_set(vector<int> &set, const int &x){
    if (set[x] != x){
        set[x] = find_set(set, set[x]);
    }
    return set[x];
}

void union_set(vector<int> &set, const int &x, const int &y){
    int x_root = find_set(set, x), y_root = find_set(set, y);
    set[min(x_root, y_root)] = max(x_root, y_root);
}

vector<int> offline_minimum(const vector<int> &A, const vector<int> &E){
    vector<int> R(A.size(), E.size());
    int pre = 0;

    for(int i = 0; i < E.size(); ++i){
        for(int j = pre; j <= E[i]; ++j){
            R[A[j]] = i;
        }
        pre = E[i] + 1;
    }

    vector<int> ret(E.size(), -1);
    vector<int> set(E.size() +1);
    iota(set.begin(), set.end(), 0);
    for(int i = 0; i < A.size(); ++i){
        if (find_set(set, R[i]) != E.size() && ret[find_set(set, R[i])] == -1){
            ret[set[R[i]]] = i;
            union_set(set, set[R[i]], set[R[i]]+1);
        }
    }
    return ret;
}


int main() {
	vector<int> ret = offline_minimum({ 4, 7, 8, 5, 6, 9, 0, 3, 1, 2 },{ 3, 5, 9 });
    for ( int i = 0; i < ret.size(); i++) {
        cout << ret[i] << " ";
    }	
	return 0;
}
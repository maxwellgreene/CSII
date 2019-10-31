function [T1, T2] = timeRank(sizes)

    nTimes = 3000;
    len = length(sizes);
    
    %N^2 Method
    T1 = zeros(1,len);
    for index = 1:len
        n = sizes(index);
        toSort = randperm(n);
        result = zeros(1,n);
        tic
        for count = 1:nTimes
            for i = 1:n
                for j = 1:n
                    if(toSort(i) > toSort(j) || (j < i && toSort(j) == toSort(i)))
                        result(i) = result(i)+1;
                    end
                end
            end
        end
        toc
        T1(index) = toc;
        
    end
    
    
    %(N^2-N)/2 Method
    T2 = zeros(1,len);
    for index = 1:len
        n = sizes(index);
        toSort = randperm(n);
        result = zeros(1,n);
        tic
        for count = 1:nTimes
            for i = 1:n
                for j = (i+1):n
                    if(toSort(j) < toSort(i))
                        result(i) = result(i)+1;
                    else
                        result(j) = result(j)+1;
                    end
                end
            end
        end
        toc
        T2(index) = toc;
    end
end
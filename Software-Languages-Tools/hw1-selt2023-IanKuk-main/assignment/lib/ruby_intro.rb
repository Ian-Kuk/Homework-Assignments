# When done, submit this entire file to the autograder.

# Part 1

def sum(arr)
  arr.sum
end

def max_2_sum (arr)
  return 0 if arr.count == 0
  return arr.at(0) if arr.count == 1
  arr = arr.max(2)
  (arr.at(0) + arr.at(1))
end

def sum_to_n? (arr, n)
  return false if arr.count <= 1
  x = false
  for i in arr
    for k in arr
      x = true if i + k == n and arr.at(i) != arr.at(k)
    end
  end
  x
end

# Part 2

def hello(name)
  "Hello, " + name
end

def starts_with_consonant? (s)
  !s.empty? and s.downcase.start_with?(/[b-df-hj-np-tv-z]/)
end

def binary_multiple_of_4? (s)
  !s.empty? and s.to_i(2)%4 ==0 and s.count('01') == s.size
end

# Part 3

class BookInStock
  attr_accessor :isbn
  attr_accessor :price

  def initialize(isbn, price)
    raise ArgumentError,
          "No ISBN given, or Price to low" if isbn.empty? or price <= 0
    @isbn = isbn
    @price = price
  end
  def price_as_string
    "$%.2f"% @price
  end
end

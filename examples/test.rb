class A_B
  class B

  end
  ##t foo: (I, I) -> S
  ##t      (I, F) -> S
  def foo(x, y)
    # comment
    z = 1
    x + y
  end
end

x, y, z = 1, 2, 3
A_B().foo(x, y)
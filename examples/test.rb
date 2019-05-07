require 'rdl'
extend RDL::Annotate

class String

end

type String, :chomp, '(Integer) -> Integer'

type String, :[], '(Integer) -> String or nil'
type String, :[], '(Integer, Integer) -> String or nil'
type String, :[], '(Range or Regexp) -> String or nil'
type String, :[], '(Regexp, Integer) -> String or nil'
type String, :[], '(Regexp, String) -> String or nil'
type String, :[], '(String) -> String or nil'
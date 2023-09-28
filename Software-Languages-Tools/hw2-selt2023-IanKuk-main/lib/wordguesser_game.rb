class WordGuesserGame

  # add the necessary class methods, attributes, etc. here
  # to make the tests in spec/wordguesser_game_spec.rb pass.

  # Get a word from remote "random word" service
  attr_accessor :word, :guesses, :wrong_guesses
  def initialize(word)
    @word = word
    @guesses = ''
    @wrong_guesses = ''
  end

  def guess(char)
  if char.nil? or char.empty? or (char =~ /[^a-zA-Z]/) != nil
    raise ArgumentError,
      ("Character is nil, No character given, or not valid character")
  end
  if @word.downcase.include?(char.downcase)
    if !@guesses.include?(char.downcase)
      @guesses += char.downcase
      return true
    end
  else
     if !@wrong_guesses.include?(char.downcase)
      @wrong_guesses += char.downcase
      return true
     end
  end
  false
  end

  def word_with_guesses
    display_word = ''
    @word.to_s.downcase.each_char{|char| if @guesses.downcase.include?(char); display_word += char else display_word += '-' end }
    display_word
  end

  def check_win_or_lose
    display_word = word_with_guesses
    if @wrong_guesses.length >= 7
      :lose
    elsif display_word == word
      :win
    else
      :play
    end

  end



  # You can test it by installing irb via $ gem install irb
  # and then running $ irb -I. -r app.rb
  # And then in the irb: irb(main):001:0> WordGuesserGame.get_random_word
  #  => "cooking"   <-- some random word
  def self.get_random_word
    require 'uri'
    require 'net/http'
    uri = URI('http://randomword.saasbook.info/RandomWord')
    Net::HTTP.new('randomword.saasbook.info').start { |http|
      return http.post(uri, "").body
    }
  end

end

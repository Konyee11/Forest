ANT	= env LC_ALL=ja_JP.UTF-8 ant
ARCHIVE	= $(shell basename `pwd`)

all:
	$(ANT) all

clean:
	$(ANT) clean

test:
	$(ANT) test

# 以下を追加、単体テストのために。
unittest:
	$(ANT) unittest

# 以下を追加、アサーションのために。
assert:
	$(ANT) assert

install:
	$(ANT) install

doc:
	$(ANT) doc

wipe: clean
	@find . -name ".DS_Store" -exec echo rm -f {} ";" -exec rm -f {} ";"
	@find .. -maxdepth 1 -name "$(ARCHIVE).zip" -exec echo rm -f {} ";" -exec rm -f {} ";"

#zip:
#	$(ANT) zip

zip: wipe
	(cd ../ ; zip -r ./$(ARCHIVE).zip ./$(ARCHIVE)/ --exclude='*/.svn/*')
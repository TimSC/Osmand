import os, string

def ReplaceStrInFile(fina, oldTxt, newTxt):
	fiIn = open(fina)
	data = fiIn.read()
	fiIn.close()

	dataMod = string.replace(data, oldTxt, newTxt)

	if data != dataMod:
		fiOut = open(fina, "w")
		fiOut.write(dataMod)
		fiOut.close()

def WalkFolders(pth):

	for f in os.listdir(pth):

		if f[0] == ".": continue #Ignore hidden files
		if f == "renameProject.py": continue #Ignore self
		c = pth+"/"+f

		if os.path.isdir(c):
			WalkFolders(c)
		if os.path.isfile(c):
			print c
			ReplaceStrInFile(c, "com.kinatomicHam", "com.kinatomicWsus")

if __name__=="__main__":
	
	WalkFolders(".")
	

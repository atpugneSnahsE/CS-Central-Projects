# Import packages
import wikipedia
import re

# Specify the title of the Wikipedia page
wiki = wikipedia.page('Bangladesh')

# Extract the plain text content of the page
text = wiki.content

# Clean text
text = re.sub(r'==.*?==+', '', text)
text = text.replace('\n', '')

# Import packages
import matplotlib.pyplot as plt
%matplotlib inline

# Define a function to plot word cloud
def plot_cloud(wordcloud):

# Set figure size
    plt.figure(figsize=(40, 30))

# Display image
    plt.imshow(wordcloud) 

# No axis details
    plt.axis("off");

# Import package
    from wordcloud import WordCloud, STOPWORDS
    
# Generate wordcloud
wordcloud = WordCloud(width = 3000, 
                      height = 2000, 
                      random_state=5, 
                      background_color='Green', 
                      colormap='Reds', 
                      collocations=False, 
                      stopwords = STOPWORDS, 
                      mask=mask).generate(text)

# Plot
plot_cloud(wordcloud)
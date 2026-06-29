@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;



    public String shortenUrl(String originalUrl) {
        originalUrl = originalUrl.trim();
        Url url = new Url(originalUrl);
        long id = urlRepository.save(url).getId();
        String shortCode = encodeBase62(id);
        url.setShortCode(shortCode);
        urlRepository.save(url);
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        Optional<Url> urlOptional = urlRepository.findByShortCode(shortCode);
        return urlOptional.map(Url::getOriginalUrl).orElse(null);
    }

    private static String encodeBase62(long number) {
        final String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(characters.charAt((int) (number % characters.length())));
            number /= characters.length();
        }
        return sb.reverse().toString();
    }

}
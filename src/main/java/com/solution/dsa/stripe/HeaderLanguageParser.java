package com.solution.dsa.stripe;

import lombok.Getter;

import java.util.*;

public class HeaderLanguageParser {

    @Getter
    public static class CustomLocale {
        private final String language;
        private String country;
        private int quality;

        public CustomLocale(String language, double quality) {
            this.language = language;
            this.quality = (int) (quality * 10);
        }

        public CustomLocale(String language, String country) {
            this.language = language;
            this.country = country;
        }

        public CustomLocale(String language, String country, double quality) {
            this.language = language;
            this.country = country;
            this.quality = (int) (quality * 10);
        }

        public boolean isWildcard() {
            return !isInvalid() && language.equals("*");
        }

        public boolean isInvalid() {
            return language == null || language.trim().isEmpty();
        }

        @Override
        public String toString() {
            return language + "-" + country;
        }
    }

    public List<String> matchingHeaders(String inputHeaderStr, List<CustomLocale> systemHeaders) {
        List<CustomLocale> clientHeaders = parseHeader(inputHeaderStr);
        clientHeaders.sort(Comparator.comparingInt(o -> o.quality));

        Set<String> memory = new HashSet<>();
        List<String> returnHeaders = new ArrayList<>();
        for (CustomLocale header : clientHeaders) {
            for (CustomLocale sysHeader : systemHeaders) {
                if (compareLocale(header, sysHeader) && !memory.contains(sysHeader.toString())) {
                    returnHeaders.add(sysHeader.toString());
                    memory.add(sysHeader.toString());
                }
            }
        }
        return returnHeaders;
    }

    private List<CustomLocale> parseHeader(String headerStr) {
        List<CustomLocale> headers = new ArrayList<>();
        for (String header : headerStr.split(",")) {
            double quality = 1.0;
            String[] qualityParam = header.trim().split(";q=");
            if (qualityParam.length == 2) quality = Double.parseDouble(qualityParam[1].trim());

            String[] headerParam = qualityParam[0].trim().split("-");
            if (headerParam.length == 1) headers.add(new CustomLocale(headerParam[0], quality));
            else headers.add(new CustomLocale(headerParam[0], headerParam[1], quality));
        }
        return headers;
    }

    private boolean compareLocale(CustomLocale locale1, CustomLocale locale2) {
        if (locale1 == null || locale1.isInvalid() || locale2 == null || locale2.isInvalid()) return false;
        else if (locale1.isWildcard() || locale2.isWildcard()) return true;

        else if (locale1.getCountry() == null || locale1.getCountry().isEmpty()
                || locale2.getCountry() == null || locale2.getCountry().isEmpty())
            return locale1.getLanguage().equals(locale2.getLanguage());

        else return locale1.getLanguage().equals(locale2.getLanguage())
                    && locale1.getCountry().equals(locale2.getCountry());
    }

    public static void main(String[] args) {
        HeaderLanguageParser obj = new HeaderLanguageParser();
        List<CustomLocale> systemHeader = new ArrayList<>() {{
            add(new CustomLocale("fr", "FR"));
            add(new CustomLocale("en", "US"));
            add(new CustomLocale("en", "CA"));
        }};
        List<String> result = obj.matchingHeaders("en-US;q=0.5, fr-CA;q=0, *", systemHeader);
        System.out.println(result);
    }
}

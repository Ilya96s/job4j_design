package ru.job4j.design.isp;

public class IspExamples {

    /**
     * Пример №1
     *
     * Нарушение принципа ISP в том, что классы, реализующие данный интерфейс будут использовать не все методы, поэтому остальные методы придется заглушить
     *
     * @author Ilya Kaltygin
     */
    interface IAutoSet {
        void getBmwSet();

        void getTeslaSet();

        void getAudiSet();
    }

    static class Bmw implements IAutoSet {

        @Override
        public void getBmwSet() {
            System.out.println("BMW set");
        }

        /**
         * Сет для tesla не используется в классе BMW
         */
        @Override
        public void getTeslaSet() {
            throw new UnsupportedOperationException();
        }

        /**
         * Сет для audi не используется в классе BMW
         */
        @Override
        public void getAudiSet() {
            throw new UnsupportedOperationException();
        }
    }

    static class Tesla implements IAutoSet {

        /**
         * Сет для bmw не используется в классе Tesla
         */
        @Override
        public void getBmwSet() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void getTeslaSet() {
            System.out.println("Tesla set");
        }

        /**
         * Сет для audi не используется в классе Tesla
         */
        @Override
        public void getAudiSet() {
            throw new UnsupportedOperationException();
        }
    }

    static class Audi implements IAutoSet {

        /**
         * Сет для bmw не используется в классе Audi
         */
        @Override
        public void getBmwSet() {
            throw new UnsupportedOperationException();
        }

        /**
         * Сет для tesla не используется в классе Audi
         */
        @Override
        public void getTeslaSet() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void getAudiSet() {
            System.out.println("Tesla set");
        }
    }

    /**
     * Пример №2
     *
     * Нарушение принципа ISP в том, что не все оружие имеет функцию перезарядки
     */
    interface IWeapon {
        void attack();

        void reload();
    }

    static class Knife implements IWeapon {

        @Override
        public void attack() {
            System.out.println("Knife attack");
        }

        /**
         * У ножа нет функции перезарядки
         */
        @Override
        public void reload() {
            throw new UnsupportedOperationException();
        }
    }

    static class MachineGun implements IWeapon {

        @Override
        public void attack() {
            System.out.println("MachineGun attack");
        }

        @Override
        public void reload() {
            System.out.println("Reload machineGun");
        }
    }

    /**
     * Пример №3
     *
     * Нарушение принципа ISP в том, что классы, реализующие данный интерфейс будут использовать не все методы, поэтому остальные методы придется заглушить
     */
    interface ISavaAndSend {
        void send();

        void save();
    }

    /**
     * Объект Saver не умеет отправлять данные
     */
    static class Saver implements ISavaAndSend {
        @Override
        public void send() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void save() {
            System.out.println("Save data");
        }
    }

    /**
     * Объект Sender не умеет сохранять данные
     */
    static class Sander implements ISavaAndSend {
        @Override
        public void send() {
            System.out.println("Send data");
        }

        @Override
        public void save() {
            throw new UnsupportedOperationException();
        }
    }

}
